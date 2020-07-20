package com.cleaner.booster.phone.repairer.app.models;

public class DeepCleanPackagesModel {
    String pkgPath;
    String pkgName;

    public DeepCleanPackagesModel() {
    }


    public DeepCleanPackagesModel(String pkgPath, String pkgName) {
        this.pkgPath = pkgPath;
        this.pkgName = pkgName;
    }

    public String getPkgPath() {
        return pkgPath;
    }

    public void setPkgPath(String pkgPath) {
        this.pkgPath = pkgPath;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
