package com.monitor.auth.constant;


/**
 * @Description: 权限枚举类
 * @author lisuo
 * @date 2018/12/4 0004下午 9:26
 */
public enum UserRoleEnum {
    SUPER_ADMIN("0", "管理员"),
    ADMIN("1", "班组长"),
    COMMON_USER("2", "工人");
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    UserRoleEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }


    public static String getNameByType(String type){
        for(UserRoleEnum userRoleEnum : UserRoleEnum.values()){
            if (type.equalsIgnoreCase(userRoleEnum.getType())){
                return userRoleEnum.getName();
            }
        }
        return null;
    }
}
