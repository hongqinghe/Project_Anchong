package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * Created by baishixin on 16/4/21.
 */
public class EditAddressResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : {"Message":{"id":32,"users_id":"29","region":"北京北京市石景山区","add_name":"白世鑫","phone":"18211168676","isdefault":"0","address":"三家店162号"}}
     */

    /**
     * Message : {"id":32,"users_id":"29","region":"北京北京市石景山区","add_name":"白世鑫","phone":"18211168676","isdefault":"0","address":"三家店162号"}
     */

    private ResultDataBean ResultData;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
        /**
         * id : 32
         * users_id : 29
         * region : 北京北京市石景山区
         * add_name : 白世鑫
         * phone : 18211168676
         * isdefault : 0
         * address : 三家店162号
         */

        private MessageBean Message;

        public MessageBean getMessage() {
            return Message;
        }

        public void setMessage(MessageBean Message) {
            this.Message = Message;
        }

        public static class MessageBean {
            private int id;
            private String users_id;
            private String region;
            private String add_name;
            private String phone;
            private String isdefault;
            private String address;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsers_id() {
                return users_id;
            }

            public void setUsers_id(String users_id) {
                this.users_id = users_id;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getAdd_name() {
                return add_name;
            }

            public void setAdd_name(String add_name) {
                this.add_name = add_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(String isdefault) {
                this.isdefault = isdefault;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            @Override
            public String toString() {
                return "MessageBean{" +
                        "id=" + id +
                        ", users_id='" + users_id + '\'' +
                        ", region='" + region + '\'' +
                        ", add_name='" + add_name + '\'' +
                        ", phone='" + phone + '\'' +
                        ", isdefault='" + isdefault + '\'' +
                        ", address='" + address + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "Message=" + Message +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EditAddressResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
