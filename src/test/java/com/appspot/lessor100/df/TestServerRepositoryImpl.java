package com.appspot.lessor100.df;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.tester.AppEngineTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestServerRepositoryImpl extends AppEngineTestCase {

    @Autowired
    private ServerRepository serverRepository;

    @Test
    public void testSaveMounts() throws Exception {
        List<Mount> mounts = new ArrayList<Mount>();
        Mount mount = new Mount();
        mount.setMountedOn("/var");
        mount.setUsageInPercent(90);
        mounts.add(mount);
        mount = new Mount();
        mount.setMountedOn("/usr");
        mount.setUsageInPercent(10);
        mounts.add(mount);
        String serverName = "ewpfront30";
        serverRepository.saveMounts(mounts, serverName);

        Server server = serverRepository.findByName(serverName);
        Assert.assertNotNull(server);
        List<MountGroup> mountGroups = server.getMountListRef().getModelList();
        Assert.assertEquals(1, mountGroups.size());
        MountGroup mountGroup = mountGroups.get(0);
        mounts = mountGroup.getMountListRef().getModelList();
        Assert.assertEquals(2, mounts.size());
    }
}
