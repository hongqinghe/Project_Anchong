package net.anchong.app.uitls;

import com.google.gson.Gson;

import net.anchong.app.entity.CertificationModel;
import net.anchong.app.entity.request.model.CertificationRequestParamModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baishixin on 16/4/11.
 */
public class JsonObjectUtils {

    public static String getCertificationJson(String name, List<CertificationModel> certificationModels) {
        ArrayList<String> qua_name = new ArrayList<>();
        ArrayList<String> explanation = new ArrayList<>();
        ArrayList<String> credentials = new ArrayList<>();

        for (CertificationModel certificationModel : certificationModels) {
            qua_name.add(certificationModel.getName());
            explanation.add(certificationModel.getDesc());
            credentials.add(certificationModel.getImg_url());
        }

        CertificationRequestParamModel certificationRequestParamModel = new CertificationRequestParamModel();
        certificationRequestParamModel.setAuth_name(name);
        certificationRequestParamModel.setQua_name(qua_name);
        certificationRequestParamModel.setExplanation(explanation);
        certificationRequestParamModel.setCredentials(credentials);

        String json = new Gson().toJson(certificationRequestParamModel);

        return json;
    }
}
