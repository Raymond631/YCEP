package cn.edu.csu.ycepspring.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResp {
    private int roleId;
    private int account;
    private String username;
    private String token;
    private int isSelectedInterest;

    public LoginResp(int roleId, int account, String username, int isSelectedInterest) {
        this.roleId = roleId;
        this.account = account;
        this.username = username;
        this.isSelectedInterest = isSelectedInterest;
    }
}
