package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;
import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

public class AppDTO implements Serializable {

    public long id;
    public String name;
    @Json(name = "package")
    @NonNull
    public String packageName;
    @Json(name = "store_id")
    public long storeId;
    @Json(name = "store_name")
    @NonNull
    public String storeName;
    @Json(name = "vername")
    @NonNull
    public String verName;
    @Json(name = "verCode")
    @NonNull
    public String verCode;
    @NonNull
    public String md5sum;
    @Json(name = "apk_tags")
    public List<String> apkTags;
    public long size;
    public long downloads;
    public long pdownloads;
    @NonNull
    public String added;
    @NonNull
    public String modified;
    @NonNull
    public String updated;
    public float rating;
    @NonNull
    public String icon;
    @NonNull
    public String graphic;
    @NonNull
    public String uptype;

    public AppDTO(long id, String name, @NonNull String packageName,
                  long storeId, @NonNull String storeName, @NonNull String verName,
                  @NonNull String verCode, @NonNull String md5sum, List<String> apkTags, long size,
                  long downloads, long pdownloads, @NonNull String added, @NonNull String modified,
                  @NonNull String updated, float rating, @NonNull String icon, @NonNull String graphic,
                  @NonNull String uptype) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.storeId = storeId;
        this.storeName = storeName;
        this.verName = verName;
        this.verCode = verCode;
        this.md5sum = md5sum;
        this.apkTags = apkTags;
        this.size = size;
        this.downloads = downloads;
        this.pdownloads = pdownloads;
        this.added = added;
        this.modified = modified;
        this.updated = updated;
        this.rating = rating;
        this.icon = icon;
        this.graphic = graphic;
        this.uptype = uptype;
    }

    @NonNull
    @Override
    public String toString() {
        return "AppDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", verName='" + verName + '\'' +
                ", verCode='" + verCode + '\'' +
                ", md5sum='" + md5sum + '\'' +
                ", apkTags=" + apkTags +
                ", size=" + size +
                ", downloads=" + downloads +
                ", pdownloads=" + pdownloads +
                ", added='" + added + '\'' +
                ", modified='" + modified + '\'' +
                ", updated='" + updated + '\'' +
                ", rating=" + rating +
                ", icon='" + icon + '\'' +
                ", graphic='" + graphic + '\'' +
                ", uptype='" + uptype + '\'' +
                '}';
    }
}
