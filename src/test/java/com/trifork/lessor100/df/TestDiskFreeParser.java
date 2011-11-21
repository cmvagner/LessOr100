package com.trifork.lessor100.df;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class TestDiskFreeParser {

    private String testDfOutput = "Filesystem                              1024-blocks      Used  Available Capacity  Mounted on\n" +
            "/dev/disk0s2                              311731616 290994664   20480952    94%    /\n" +
            "devfs                                           194       194          0   100%    /dev\n" +
            "map -hosts                                        0         0          0   100%    /net\n" +
            "map auto_home                                     0         0          0   100%    /home\n" +
            "localhost:/nGSQcpeZuEZoNQcY2amuUs         311731616 311731616          0   100%    /Volumes/MobileBackups\n" +
            "/dev/disk1s2                                  15000      6864       8136    46%    /Volumes/iExplorer Installer\n" +
            "/dev/disk5s2                                 149116    146876       2240    99%    /Volumes/AppCode Beta\n" +
            "afp_0UZ32m1ubPNA00m5k50Pyetl-1.2e00002f  1951417392 848393068 1103024324    44%    /Volumes/Claus Myglegaard Vagner's T\n" +
            "/dev/disk4s2                             1951417360 848425804 1102991556    44%    /Volumes/Time Machine Backups";

    @Test
    public void testParse() throws Exception {
        DiskFreeParser diskFreeParser = new DiskFreeParser();
        List<Mount> mounts = diskFreeParser.parse(testDfOutput);
        Assert.assertEquals(9, mounts.size());
        Mount mount = mounts.get(0);
        Assert.assertEquals(94.0, mount.getUsageInPercent());
        Assert.assertEquals("/", mount.getMountedOn());
        mount = mounts.get(1);
        Assert.assertEquals(100.0, mount.getUsageInPercent());
        Assert.assertEquals("/dev", mount.getMountedOn());
        mount = mounts.get(2);
        Assert.assertEquals(100.0, mount.getUsageInPercent());
        Assert.assertEquals("/net", mount.getMountedOn());
        mount = mounts.get(3);
        Assert.assertEquals(100.0, mount.getUsageInPercent());
        Assert.assertEquals("/home", mount.getMountedOn());
        mount = mounts.get(4);
        Assert.assertEquals(100.0, mount.getUsageInPercent());
        Assert.assertEquals("/Volumes/MobileBackups", mount.getMountedOn());
        mount = mounts.get(5);
        Assert.assertEquals(46.0, mount.getUsageInPercent());
        Assert.assertEquals("/Volumes/iExplorer Installer", mount.getMountedOn());
        mount = mounts.get(6);
        Assert.assertEquals(99.0, mount.getUsageInPercent());
        Assert.assertEquals("/Volumes/AppCode Beta", mount.getMountedOn());
        mount = mounts.get(7);
        Assert.assertEquals(44.0, mount.getUsageInPercent());
        Assert.assertEquals("/Volumes/Claus Myglegaard Vagner's T", mount.getMountedOn());
        mount = mounts.get(8);
        Assert.assertEquals(44.0, mount.getUsageInPercent());
        Assert.assertEquals("/Volumes/Time Machine Backups", mount.getMountedOn());
    }
}
