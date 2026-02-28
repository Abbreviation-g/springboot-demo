package com.my.learning;


import com.my.learning.grpc.api.common.PageResult;
import com.my.learning.grpc.api.common.Result;
import com.my.learning.grpc.api.user.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Dominick Li
 * @CreateTime 2024/3/21 15:41
 **/
@GrpcService
public class UserServiceApi extends UserServiceApiGrpc.UserServiceApiImplBase {

    @Override
    public void login(LoginInfoDTO request, StreamObserver<LoginResultVo> responseObserver) {
        LoginResultVo.Builder builder = LoginResultVo.newBuilder();
        Result.Builder rBuilder = Result.newBuilder();
        if (!"admin".equals(request.getUsername()) || !"123456".equals(request.getPassword())) {
            rBuilder.setOk(false);
            rBuilder.setCode(101);
            rBuilder.setMessage("用户名或密码错误！");
        } else {
            rBuilder.setOk(true);
            rBuilder.setCode(200);
            rBuilder.setMessage("登录成功!");
            builder.setData(UserInfo.newBuilder()
                    .setToken(UUID.randomUUID().toString())
                    .setId(1)
                    .build());
        }
        builder.setResult(rBuilder);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    List<RUser> userInfoList = Arrays.asList(
            RUser.newBuilder()
                    .setId(1).setUsername("admin").setPassword("123456").setEmail("admin@qq.com").setAge(18)
                    .build(),
            RUser.newBuilder()
                    .setId(2).setUsername("test").setPassword("123456").setEmail("test@qq.com").setAge(28)
                    .build(),
            RUser.newBuilder()
                    .setId(3).setUsername("test2").setPassword("123456").setEmail("test@qq.com").setAge(28)
                    .build()
    );

    @Override
    public void selectUserList(QueryDTO request, StreamObserver<PageResultVo> responseObserver) {
        PageResultVo.Builder builder = PageResultVo.newBuilder();

        builder.setResult(Result.newBuilder().setOk(true));
        List<RUser> findUsers;

        if (StringUtils.isEmpty(request.getUsername())) {
            findUsers = userInfoList;
        } else {
            findUsers = userInfoList.stream().filter(user -> user.getUsername().equals(request.getUsername())).collect(Collectors.toList());
        }

        if (request.getAge() > 0) {
            findUsers = findUsers.stream().filter(user -> user.getAge() == request.getAge()).collect(Collectors.toList());
        }

        long total = findUsers.size(), pages = 0;
        if (total > 0) {
            pages = total % request.getPage().getSize() > 0 ? total / request.getPage().getSize() + 1 : total / request.getPage().getSize();
        }

        //分页筛选数据
        findUsers = findUsers.stream().skip((request.getPage().getCurrent() - 1) * request.getPage().getSize()).limit(request.getPage().getSize()).collect(Collectors.toList());

        //通过addAll添加集和
        builder.addAllData(findUsers);

        //设置分页信息
        builder.setPage(PageResult.newBuilder()
                .setTotalElements(total)
                .setTotalPages(pages));

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }


}
