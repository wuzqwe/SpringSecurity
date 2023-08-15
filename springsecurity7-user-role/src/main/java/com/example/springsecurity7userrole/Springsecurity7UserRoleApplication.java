package com.example.springsecurity7userrole;

import com.example.springsecurity7userrole.mapper.SysUserMapper;
import com.example.springsecurity7userrole.pojo.SysUser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MapperScan(basePackages = "com.example.springsecurity7userrole.mapper")
@SpringBootApplication
public class Springsecurity7UserRoleApplication {

    @Resource
    private SysUserMapper sysUserMapper;

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity7UserRoleApplication.class, args);
    }

    //@PostConstruct
    public void jdbcInit(){

        Date curDate = new Date();
        PasswordEncoder encoder=new BCryptPasswordEncoder();//加密

        List<GrantedAuthority> list=new ArrayList<>();
        //参数角色名称，需要以“ROLE_”开头，后面加上自定义的角色名称
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+"READ");
        list.add(authority);

        SysUser sysUser=new SysUser("lisi",encoder.encode("456"),"李四",true,true,true,true,curDate,curDate,list);

        sysUserMapper.insertSysUser(sysUser);
        List<GrantedAuthority> list2=new ArrayList<>();
        //参数角色名称，需要以“ROLE_”开头，后面加上自定义的角色名称
        GrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_"+"ADMIN");
        GrantedAuthority authority3 = new SimpleGrantedAuthority("ROLE_"+"USER");
        list2.add(authority2);
        list2.add(authority3);
        SysUser sysUser2=new SysUser("admin",encoder.encode("admin"),"管理员",true,true,true,true,curDate,curDate,list2);

        sysUserMapper.insertSysUser(sysUser2);

    }

}
