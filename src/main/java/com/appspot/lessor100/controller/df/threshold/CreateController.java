package com.appspot.lessor100.controller.df.threshold;

import java.util.logging.Logger;

import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

import com.appspot.lessor100.controller.AbstractBaseController;
import com.appspot.lessor100.df.Server;
import com.appspot.lessor100.df.ServerRepository;
import com.appspot.lessor100.df.Threshold;
import com.appspot.lessor100.df.ThresholdMeta;
import com.google.appengine.api.datastore.KeyFactory;

public class CreateController extends AbstractBaseController {

    private static final Logger logger = Logger.getLogger(CreateController.class.getName());

    @Override
    protected Navigation run() throws Exception {
        ServerRepository serverRepository = getServerRepository();
        requestScope("servers", serverRepository.getAllServers());
        if (!validate()) {
            return forward("create.jsp");
        }
        RequestMap requestMap = new RequestMap(request);
        Threshold threshold = new Threshold();
        BeanUtil.copy(request, threshold);
        Server server = serverRepository.findByKey(KeyFactory.stringToKey((String) requestMap.get("server")));
        serverRepository.saveThreshold(threshold, server);
        return forward("create.jsp");
    }

    protected boolean validate() {
        Validators validators = new Validators(request);
        validators.add(ThresholdMeta.get().mount, validators.required());
        return validators.validate();
    }

}
