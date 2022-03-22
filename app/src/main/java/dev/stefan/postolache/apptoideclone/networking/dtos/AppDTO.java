package dev.stefan.postolache.apptoideclone.networking.dtos;

import androidx.annotation.NonNull;
import com.squareup.moshi.Json;

import java.util.List;

public class AppDTO {

    private long id;
    private String name;
    @Json(name = "package")
    @NonNull
    private String packageName;
    @Json(name = "store_id")
    private long storeId;
    @Json(name = "store_name")
    @NonNull
    private String storeName;
    @Json(name = "ver_name")
    @NonNull
    private String verName;
    @Json(name = "verCode")
    @NonNull
    private String verCode;
    @NonNull
    private String md5sum;
    @Json(name = "apk_tags")
    private List<String> apkTags;
    private long size;
    private long downloads;
    private long pdownloads;
    @NonNull
    private String added;
    @NonNull
    private String modified;
    @NonNull
    private String updated;
    private float rating;
    @NonNull
    private String icon;
    @NonNull
    private String graphic;
    @NonNull
    private String uptype;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(@NonNull String packageName) {
        this.packageName = packageName;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    @NonNull
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(@NonNull String storeName) {
        this.storeName = storeName;
    }

    @NonNull
    public String getVerName() {
        return verName;
    }

    public void setVerName(@NonNull String verName) {
        this.verName = verName;
    }

    @NonNull
    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(@NonNull String verCode) {
        this.verCode = verCode;
    }

    @NonNull
    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(@NonNull String md5sum) {
        this.md5sum = md5sum;
    }

    public List<String> getApkTags() {
        return apkTags;
    }

    public void setApkTags(List<String> apkTags) {
        this.apkTags = apkTags;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
    }

    public long getPdownloads() {
        return pdownloads;
    }

    public void setPdownloads(long pdownloads) {
        this.pdownloads = pdownloads;
    }

    @NonNull
    public String getAdded() {
        return added;
    }

    public void setAdded(@NonNull String added) {
        this.added = added;
    }

    @NonNull
    public String getModified() {
        return modified;
    }

    public void setModified(@NonNull String modified) {
        this.modified = modified;
    }

    @NonNull
    public String getUpdated() {
        return updated;
    }

    public void setUpdated(@NonNull String updated) {
        this.updated = updated;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @NonNull
    public String getIcon() {
        return icon;
    }

    public void setIcon(@NonNull String icon) {
        this.icon = icon;
    }

    @NonNull
    public String getGraphic() {
        return graphic;
    }

    public void setGraphic(@NonNull String graphic) {
        this.graphic = graphic;
    }

    @NonNull
    public String getUptype() {
        return uptype;
    }

    public void setUptype(@NonNull String uptype) {
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
