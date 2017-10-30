package net.anchong.app.entity.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lihaiyi on 15/12/9.
 */
public class AddressAreaModel implements Parcelable {
    public String id;
    public String province;
    public String city;
    public String district;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
    }

    public AddressAreaModel() {
    }

    protected AddressAreaModel(Parcel in) {
        this.id = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
    }

    public static final Creator<AddressAreaModel> CREATOR = new Creator<AddressAreaModel>() {
        public AddressAreaModel createFromParcel(Parcel source) {
            return new AddressAreaModel(source);
        }

        public AddressAreaModel[] newArray(int size) {
            return new AddressAreaModel[size];
        }
    };
}
