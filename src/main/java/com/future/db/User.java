package com.future.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述:
 *
 * @author future
 * @date 2021-08-30 16:53
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Integer id;

    private String name;

    private Integer age;

}