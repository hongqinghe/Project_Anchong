package net.anchong.app.entity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lihaiyi on 15/12/5.
 */
public class AddressModel implements Parcelable {
    public String id;
    public String user_id;
    public String recipient_name;
    public String recipient_phone;
    public String recipient_id_no;
    public String area_id;
    public String address;
    public AddressAreaModel area;
    public boolean isSelect;
    public int is_default;

    public AddressModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.user_id);
        dest.writeString(this.recipient_name);
        dest.writeString(this.recipient_phone);
        dest.writeString(this.recipient_id_no);
        dest.writeString(this.area_id);
        dest.writeString(this.address);
        dest.writeParcelable(this.area, 0);
        dest.writeByte(isSelect ? (byte) 1 : (byte) 0);
        dest.writeInt(this.is_default);
    }

    protected AddressModel(Parcel in) {
        this.id = in.readString();
        this.user_id = in.readString();
        this.recipient_name = in.readString();
        this.recipient_phone = in.readString();
        this.recipient_id_no = in.readString();
        this.area_id = in.readString();
        this.address = in.readString();
        this.area = in.readParcelable(AddressAreaModel.class.getClassLoader());
        this.isSelect = in.readByte() != 0;
        this.is_default = in.readInt();
    }

    public static final Creator<AddressModel> CREATOR = new Creator<AddressModel>() {
        public AddressModel createFromParcel(Parcel source) {
            return new AddressModel(source);
        }

        public AddressModel[] newArray(int size) {
            return new AddressModel[size];
        }
    };
}
