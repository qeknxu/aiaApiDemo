package com.aiaApiDemo.swagger2.web.controller;

import com.aiaApiDemo.swagger2.model.UserInfo;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "帳戶")
@RequestMapping("/userInfo")
@RestController
public class UserInfoController {
    @ApiOperation(value = "登錄接口-多值傳值方式", notes = "輸入用帳戶、密碼登錄")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "輸入正確!", response = UserInfo.class, responseContainer = "userInfo"),
            @ApiResponse(code = 405, message = "帳號或密碼錯誤!")
    })
    @ApiImplicitParam(name = "map", value = "{\"userName\":\"aia\",\"passWord\":\"123\"}")
    @RequestMapping(value = "loginForMap", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<UserInfo> loginForMap(@RequestBody Map<String, String> map) {
        
    	// 驗證帳號、密碼
    	if (!map.get("userName").equalsIgnoreCase("aia") || !map.get("passWord").equalsIgnoreCase("123")) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
        
        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setUserName("aia");
        user.setFirstName("友邦人壽");
        user.setLastName("友邦人壽");
        user.setEmail("aia@aia.com");
        user.setUserStatus(1);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "登錄接口-多值傳值方式", notes = "輸入用帳戶、密碼登錄")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "輸入正確!", response = UserInfo.class, responseContainer = "userInfo"),
            @ApiResponse(code = 400, message = "自訂訊息!", response = UserInfo.class, responseContainer = "userInfo"),
            @ApiResponse(code = 405, message = "帳號或密碼錯誤!")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "帳戶", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "passWord",value = "密碼", required = true, dataType = "string",paramType = "query"),
    })
    @RequestMapping(value = "loginForParams", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<UserInfo> loginForMap(@RequestParam String userName, @RequestParam String passWord) {
        // 驗證帳號、密碼
    	if (!userName.equalsIgnoreCase("aia") || !passWord.equalsIgnoreCase("123")) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
        
    	UserInfo user = new UserInfo();
        user.setId(1L);
        user.setUserName("aia");
        user.setFirstName("友邦人壽");
        user.setLastName("友邦人壽");
        user.setEmail("aia@aia.com");
        user.setUserStatus(1);
        return ResponseEntity.ok(user);
    }

}
