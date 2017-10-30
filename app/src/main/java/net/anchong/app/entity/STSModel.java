package net.anchong.app.entity;

/**
 * Created by baishixin on 16/4/6.
 */
public class STSModel {

    /**
     * status : 200
     * AccessKeyId : STS.MhYARAMXdWjttTNxCSxFT5Y7a
     * AccessKeySecret : Bt6WwVQqYsWFrecdzbGeuqE6ebWrMyu8yUDvXrzsaUa5
     * Expiration : 1459932604
     * SecurityToken : CAESlgMIARKAARNLaxt2MwXY7WGUJ2F9lNt4Yd0U2cQrd5of08oFxDqc5abneL06B0zqxTn3sFfLh8N7tCS/1N6/kU/l7ypWA5GK8f/gwgjYRLzneY8s5a8cJc3eUf64vbFvsB9SftQ+QFNS5H3cSNY6r7ovK2MHnnUbVCU2ogRKwRv6/54qYAW4Gh1TVFMuTWhZQVJBTVhkV2p0dFROeENTeEZUNVk3YSISMzIxNTg4NjU3OTE5ODY4NTI5KgtjbGllbnRfbmFtZTDv0I7Wvio6BlJzYU1ENUJaCgExGlUKBUFsbG93Eh8KDEFjdGlvbkVxdWFscxIGQWN0aW9uGgcKBW9zczoqEisKDlJlc291cmNlRXF1YWxzEghSZXNvdXJjZRoPCg1hY3M6b3NzOio6KjoqShAxNTg2NjM2NTg0MTIwNzI0UgUyNjg0MloPQXNzdW1lZFJvbGVVc2VyYABqEjMyMTU4ODY1NzkxOTg2ODUyOXIbYWxpeXVub3NzdG9rZW5nZW5lcmF0b3Jyb2xleJTj7++Z4egC
     */

    private int status;
    private String AccessKeyId;
    private String AccessKeySecret;
    private int Expiration;
    private String SecurityToken;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String AccessKeyId) {
        this.AccessKeyId = AccessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String AccessKeySecret) {
        this.AccessKeySecret = AccessKeySecret;
    }

    public int getExpiration() {
        return Expiration;
    }

    public void setExpiration(int Expiration) {
        this.Expiration = Expiration;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String SecurityToken) {
        this.SecurityToken = SecurityToken;
    }
}
