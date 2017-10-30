package net.anchong.app.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * 内容提供者
 * Created by baishixin on 16/3/15.
 */
public class AppContentProvider extends ContentProvider {

    public static final String AUTHORITY = "net.anchong.app";

    private static final String DATABASE_NAME = "anchong.db";

    private static final int DATABASE_VERSION = 1;


    private DbHelper mOpenHelper;


    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


    /**
     * 安虫用户表
     */
    public interface Anchong_Users {
        String TABLE_NAME = "anchong_users";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.PASSWORD + " VARCHAR(256) NOT NULL COMMENT '密码',"
                + COLUMNS.MOBILE + " VARCHAR(11) NOT NULL COMMENT '电话',"
                + COLUMNS.EMAIL + " VARCHAR(128) DEFAULT NULL COMMENT '邮箱',"
                + COLUMNS.CTIME + " DATETIME NOT NULL COMMENT '注册时间',"
                + COLUMNS.CERTIFICATION + " varchar(256) NOT NULL DEFAULT '0' COMMENT '0(0代表未商家认证 1代表商家认证)',"
                + COLUMNS.OPEN + " varchar(256) NOT NULL DEFAULT '0' COMMENT '0(0代表未开通商铺 1代表已开通商铺)')";

        String DEFAULT_SORT_ORDER = COLUMNS.ID + " ASC";

        String WEIGHT_SORT_ORDER = COLUMNS.ID + " DESC";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_users");

        interface COLUMNS {

            /**
             * 用户id AUTO_INCREMENT
             */
            String ID = "id";

            /**
             * 登录密码 varchar(256) NOT NULL
             */
            String PASSWORD = "password";

            /**
             * 登录手机号 varchar(11) NOT NULL
             */
            String MOBILE = "mobile";

            /**
             * 邮箱 varchar(128) DEFAULT NULL
             */
            String EMAIL = "email";

            /**
             * 注册时间 datetime NOT NULL
             */
            String CTIME = "ctime";

            /**
             * 商家认证 NOT NULL 0(0代表未商家认证 1代表商家认证)
             */
            String CERTIFICATION = "certification";

            /**
             * 开通商铺 NOT NULL 0(0代表未开通商铺 1代表已开通商铺)
             */
            String OPEN = "open";
        }
    }


    /**
     * 安虫用户信息表
     */
    public interface Anchong_UserMessages {
        String TABLE_NAME = "anchong_usermessages";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.CONTACT + " VARCHAR(32) NOT NULL COMMENT '联系人',"
                + COLUMNS.ACCOUNT + " VARCHAR(32) NOT NULL COMMENT '账号',"
                + COLUMNS.PASSWORD + " VARCHAR(256) NOT NULL COMMENT '密码',"
                + COLUMNS.QQ + " VARCHAR(50) DEFAULT NULL COMMENT 'QQ',"
                + COLUMNS.EMAIL + " varchar(128) DEFAULT NULL COMMENT '邮箱')";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_usermessages");

        interface COLUMNS {
            String ID = "id";
            //联系人
            String CONTACT = "contact";
            //账号
            String ACCOUNT = "account";
            String PASSWORD = "password";
            String QQ = "qq";
            String EMAIL = "email";
        }
    }


    /**
     * 收货地址表
     */
    public interface Anchong_Address {
        String TABLE_NAME = "anchong_address";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.PROVINCE + " VARCHAR(32) NOT NULL COMMENT '省份',"
                + COLUMNS.CITY + " VARCHAR(32) NOT NULL COMMENT '市',"
                + COLUMNS.AREA + " VARCHAR(32) NOT NULL COMMENT '区',"
                + COLUMNS.SCOPE + " VARCHAR(128) DEFAULT NULL COMMENT '区域范围',"
                + COLUMNS.ADDRESS + " VARCHAR(255) NOT NULL COMMENT '详细地址')";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_address");

        interface COLUMNS {
            String ID = "id";
            String PROVINCE = "province";
            String CITY = "city";
            String AREA = "area";
            String SCOPE = "scope";
            String ADDRESS = "address";
        }
    }


    /**
     * 银行账户表
     */
    public interface Anchong_Thebankaccount {
        String TABLE_NAME = "anchong_thebankaccount";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.BANKINFO + " VARCHAR(32) NOT NULL COMMENT '银行信息',"
                + COLUMNS.BRANCH + " VARCHAR(32) NOT NULL COMMENT '支行',"
                + COLUMNS.ACCOUNTNAME + " VARCHAR(32) NOT NULL COMMENT '户名',"
                + COLUMNS.ACCOUNT + " BIGINT(20) NOT NULL COMMENT '账号(银行卡号)')";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_thebankaccount");

        interface COLUMNS {
            String ID = "id";
            String BANKINFO = "bankinfo";
            String BRANCH = "branch";
            String ACCOUNTNAME = "accountname";
            String ACCOUNT = "account";
        }
    }

    /**
     * 商家个体认证表
     */
    public interface Anchong_Mcertification_Individual {
        String TABLE_NAME = "anchong_mcertification_individual";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.NAME + " VARCHAR(32) NOT NULL COMMENT '姓名',"
                + COLUMNS.IDCARD + " BIGINT(20) NOT NULL COMMENT '身份证',"
                + COLUMNS.COLUMNS_CASE + " TEXT COMMENT '案例(项目服务简介)')";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_mcertification_individual");

        interface COLUMNS {
            String ID = "id";
            String NAME = "name";
            String IDCARD = "idcard";
            String COLUMNS_CASE = "case";
        }
    }

    /**
     * 商家认证企业表
     */
    public interface Anchong_Mcertification_Enterprise {
        String TABLE_NAME = "anchong_mcertification_enterprise";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.ENTERPRISENAME + " VARCHAR(128) NOT NULL COMMENT '企业名称',"
                + COLUMNS.LICENSE + " VARCHAR(255) NOT NULL COMMENT '营业执照',"
                + COLUMNS.QUALIFICATION + " TEXT COMMENT '资质(如企业管理体系认证)')";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_mcertification_enterprise");

        interface COLUMNS {
            String ID = "id";
            String ENTERPRISENAME = "enterprisename";
            String LICENSE = "license";
            String QUALIFICATION = "qualification";
        }
    }


    /**
     * 商铺申请表
     */
    public interface Anchong_Shops {
        String TABLE_NAME = "anchong_shops";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMNS.ID + " INTEGER  PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',"
                + COLUMNS.NAME + " VARCHAR(128) NOT NULL COMMENT '商铺名称',"
                + COLUMNS.MAINBRAND + " VARCHAR(32) NOT NULL COMMENT '主营品牌',"
                + COLUMNS.QUALIFICATION + " TEXT NOT NULL COMMENT '品牌授权(书)',"
                + COLUMNS.CATEGORY + " VARCHAR(32) NOT NULL COMMENT '主营类别',"
                + COLUMNS.INTRODUCTION + " VARCHAR(255) DEFAULT NULL COMMENT '店铺简介',"
                + COLUMNS.PREMISES + " VARCHAR(128) NOT NULL COMMENT '经营地',"
                + COLUMNS.AUDIT + " int(255) NOT NULL DEFAULT '0' COMMENT '资料审核')";

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/anchong_shops");

        interface COLUMNS {
            String ID = "id";
            String NAME = "name";
            String MAINBRAND = "mainbrand";
            String QUALIFICATION = "qualification";
            String CATEGORY = "category";
            String INTRODUCTION = "introduction";
            String PREMISES = "premises";
            String AUDIT = "audit";
        }
    }


    /**
     * 建库，建表语句在这里
     * 数据库版本发生改变的时候在 onUpgrade 方法中更新表结构。
     */
    private static class DbHelper extends SQLiteOpenHelper {

        private Context context;

        DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Anchong_Users.CREATE_TABLE);
            db.execSQL(Anchong_UserMessages.CREATE_TABLE);
            db.execSQL(Anchong_Address.CREATE_TABLE);
            db.execSQL(Anchong_Thebankaccount.CREATE_TABLE);
            db.execSQL(Anchong_Mcertification_Individual.CREATE_TABLE);
            db.execSQL(Anchong_Mcertification_Enterprise.CREATE_TABLE);
            db.execSQL(Anchong_Shops.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}
