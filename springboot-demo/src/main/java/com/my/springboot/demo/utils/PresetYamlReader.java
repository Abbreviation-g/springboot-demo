package com.my.springboot.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PresetYamlReader {

    /**
     * 将YAML转换为JSON
     *
     * @param yamlString
     * @return
     */
    public static String getJsonFromYaml(String yamlString) {
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            Object yamlObject = yamlMapper.readValue(yamlString, Object.class);
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(yamlObject);
        } catch (IOException e) {
            log.error("将YAML转换为JSON", e);
        }
        return null;
    }

    public static String readYamlAndReplace(String yamlPath, Map<String, String> replacements) throws IOException {
        try (InputStream resourceStream = PresetYamlReader.class.getClassLoader().getResourceAsStream(yamlPath)) {
            assert resourceStream != null;
            String yamlContent = new String(resourceStream.readAllBytes());
            return replacePlaceholdersWithRegex(yamlContent, replacements);
        }
    }

    /**
     * 使用正则表达式替换字符串中的占位符
     *
     * @param content      原始字符串内容
     * @param replacements 占位符和实际值的映射关系
     * @return 替换后的字符串
     */
    public static String replacePlaceholdersWithRegex(String content, Map<String, String> replacements) {
        // 定义正则表达式：匹配 {name} 形式的占位符，其中 name 是一个或多个单词字符 (\w+)
        Pattern pattern = Pattern.compile("\\{(.+?)\\}");

        Matcher matcher = pattern.matcher(content);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            String placeholderKey = matcher.group(1);

            String replacementValue = replacements.get(placeholderKey);

            if (replacementValue != null) {
                matcher.appendReplacement(sb, replacementValue);
            } else {
                // 如果没有找到对应的值，可以选择保留原占位符，或者替换为空字符串，或者抛出异常
                // 这里选择保留原占位符
                log.warn("未找到占位符 '{}' 的替换值，保留原样。", fullMatch);
                matcher.appendReplacement(sb, Matcher.quoteReplacement(fullMatch));
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
