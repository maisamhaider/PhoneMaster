package com.example.phonemaster.models;

public class DeepCleanDocsModel {
    String docPath;
    String docName;

    public DeepCleanDocsModel() {
    }

    public DeepCleanDocsModel(String docPath, String docName) {
        this.docPath = docPath;
        this.docName = docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
