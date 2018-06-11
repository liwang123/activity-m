package org.trustnote.activity.common.pojo;

public class DownloadStatistics {
    private Integer id;

    private Integer androidSum;

    private Integer iosSum;

    private Integer windowsSum;

    private Integer macSum;

    private Integer linuxSum;

    private Integer githubSum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAndroidSum() {
        return androidSum;
    }

    public void setAndroidSum(Integer androidSum) {
        this.androidSum = androidSum;
    }

    public Integer getIosSum() {
        return iosSum;
    }

    public void setIosSum(Integer iosSum) {
        this.iosSum = iosSum;
    }

    public Integer getWindowsSum() {
        return windowsSum;
    }

    public void setWindowsSum(Integer windowsSum) {
        this.windowsSum = windowsSum;
    }

    public Integer getMacSum() {
        return macSum;
    }

    public void setMacSum(Integer macSum) {
        this.macSum = macSum;
    }

    public Integer getLinuxSum() {
        return linuxSum;
    }

    public void setLinuxSum(Integer linuxSum) {
        this.linuxSum = linuxSum;
    }

    public Integer getGithubSum() {
        return githubSum;
    }

    public void setGithubSum(Integer githubSum) {
        this.githubSum = githubSum;
    }
}