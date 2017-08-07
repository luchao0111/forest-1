package com.thebeastshop.forest.executors.url;

import com.thebeastshop.forest.converter.json.ForestJsonConverter;
import com.thebeastshop.forest.http.ForestRequest;
import com.thebeastshop.forest.mapping.MappingTemplate;
import com.thebeastshop.forest.utils.RequestNameValue;
import com.thebeastshop.forest.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author gongjun[jun.gong@thebeastshop.com]
 * @since 2017-05-19 14:11
 */
public class QueryableURLBuilder extends URLBuilder {

    @Override
    public String buildUrl(ForestRequest request) {
        String url = request.getUrl();
        List<RequestNameValue> data = request.getDataNameValueList();
        StringBuilder paramBuilder = new StringBuilder();
        ForestJsonConverter jsonConverter = request.getConfiguration().getJsonCoverter();
        for (int i = 0; i < data.size(); i++) {
            RequestNameValue nameValue = data.get(i);
            paramBuilder.append(nameValue.getName());
            String value = MappingTemplate.getParameterValue(jsonConverter, nameValue.getValue());
            paramBuilder.append('=');
            if (StringUtils.isNotEmpty(value)) {
                String encodedValue = null;
                try {
                    encodedValue = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
                if (encodedValue != null) {
                    paramBuilder.append(encodedValue);
                }
            }
            if (i < data.size() - 1) {
                paramBuilder.append('&');
            }
        }
        String query = paramBuilder.toString();
        if (StringUtils.isNotEmpty(query)) {
            return url + "?" + query;
        }
        return url;
    }

}
