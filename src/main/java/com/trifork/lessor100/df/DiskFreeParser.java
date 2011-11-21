package com.trifork.lessor100.df;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class DiskFreeParser {

    private static final Logger logger = Logger.getLogger(DiskFreeParser.class.getName());

    public List<Mount> parse(String content) {
        List<String> lines = toLines(content);
        List<Mount> mounts = new ArrayList<Mount>();
        for (String line : lines) {
            Mount mount = new Mount();
            int percentCharIndex = line.indexOf("%");
            if (percentCharIndex != -1) {
                String usageInPercent = line.substring(percentCharIndex - 3, percentCharIndex).trim();
                String mountedOn = line.substring(percentCharIndex + 1).trim();
                try {
                    mount.setUsageInPercent(Double.valueOf(usageInPercent));
                } catch (NumberFormatException e) {
                    logger.finest(String.format("could not parse [%s] to a double found in line [%s]", usageInPercent, line));
                    continue;
                }
                mount.setMountedOn(mountedOn);
                mounts.add(mount);
            }
        }
        return mounts;
    }

    private List<String> toLines(String content) {
        return Arrays.asList(content.split("\n"));
    }
}
