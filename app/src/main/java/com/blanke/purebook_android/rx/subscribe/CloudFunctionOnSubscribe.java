package com.blanke.purebook_android.rx.subscribe;

import com.avos.avoscloud.AVCloud;
import com.blanke.purebook_android.rx.subscribe.base.BaseCloudOnSubscribe;

import java.util.HashMap;

//TODO:
public class CloudFunctionOnSubscribe<T> extends BaseCloudOnSubscribe<T> {
    private HashMap<String, String> params;
    private String cloudFunctionName;

    public CloudFunctionOnSubscribe(String cloudFunctionName, HashMap<String, String> params) {
        this.cloudFunctionName = cloudFunctionName;
        this.params = params;
    }

    @Override
    protected T execute() throws Exception {
        return AVCloud.rpcFunction(cloudFunctionName,params);
    }
}
