package com.my.learning;

import com.ljm.boot.grpc.api.common.PageQuery;
import com.ljm.boot.grpc.api.common.PageResult;
import com.ljm.boot.grpc.api.user.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Dominick Li
 * @CreateTime 2024/3/21 17:01
 **/
@RequestMapping
@RestController
public class UserController {
    @GrpcClient("grpc-server")
    private UserServiceApiGrpc.UserServiceApiBlockingStub serviceApiBlockingStub;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        LoginInfoDTO loginInfoDTO = LoginInfoDTO.newBuilder()
                .setUsername(username)
                .setPassword(password).build();
        LoginResultVo loginResultVo = serviceApiBlockingStub.login(loginInfoDTO);
        String result;
        if (loginResultVo.getResult().getOk()) {
            result = String.format("登录成功，token=%s, userId=%d", loginResultVo.getData().getToken(), loginResultVo.getData().getId());
        } else {
            result = String.format("登录失败，code=%d,msg=%s", loginResultVo.getResult().getCode(), loginResultVo.getResult().getMessage());
        }
        return result;
    }

    @PostMapping("/users")
    public PageVO<UserVO> selectUserList(@RequestParam(required = false) String username, @RequestParam(required = false, defaultValue = "0") Integer age, @RequestParam(required = false, defaultValue = "10") long pageSize, @RequestParam(required = false, defaultValue = "1") long pageNum) {
        PageResultVo result = serviceApiBlockingStub.selectUserList(
                QueryDTO.newBuilder()
                        .setPage(PageQuery.newBuilder().setSize(pageSize).setCurrent(pageNum).build())
                        .setUsername(username)
                        .setAge(age)
                        .build());
        //转换成页面成显示层对象
        List<UserVO> userVOList = result.getDataList().stream().map(UserVO::new).collect(Collectors.toList());
        return new PageVO<>(result.getPage(), userVOList);
    }


    public static class PageVO<T> {
        private long totalElements;
        private long totalPages;

        private List<T> list;

        public PageVO(PageResult page, List<T> list) {
            BeanUtils.copyProperties(page, this);
            this.list = list;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public List<T> getList() {
            return list;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public void setTotalPages(long totalPages) {
            this.totalPages = totalPages;
        }

    }

    public static class UserVO {
        private int id;
        private String username;
        private String email;
        private int age;

        public UserVO(RUser rUser) {
            BeanUtils.copyProperties(rUser, this);
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
