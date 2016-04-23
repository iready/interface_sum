package per.zyq.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import per.zouyq.excel.ExcelUtils;
import per.zouyq.excel.entity.Excel_Sheet;
import per.zyq.excel.jsonEntity.ETable;
import per.zyq.web.ServletFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/23.
 */
public class index extends Controller {
    public static Map<String, entity_excel> content = new HashMap<String, entity_excel>();

    public void index() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        try {
            ETable et = JSONObject.parseObject(getPara("json"), ETable.class);
            content.put(uuid, new entity_excel(et.getName(), new Excel_Sheet[]{new Excel_Sheet(et.getSheetName(), et.getContent())}, System.currentTimeMillis()));
            renderText("/dowland/" + uuid);
        } catch (Exception e) {
            renderText("参数错误");
        }
    }

    public void dowland() {
        String s = getPara(0);
        if (s != null && content.containsKey(s)) {
            content.get(s).exec();
            content.remove(s);
            renderNull();
            return;
        }
        renderText("未找到下载文件");
    }

    class entity_excel {
        private String file_name;
        private Excel_Sheet[] sheets;
        private Long time;

        public entity_excel(String file_name, Excel_Sheet[] sheets, Long time) {
            this.file_name = file_name;
            this.sheets = sheets;
            this.time = time;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public void exec() {
            ServletFile.renderBytes(ExcelUtils.JACOBtoExcel(sheets), getResponse(), file_name + ".xls");
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public Excel_Sheet[] getSheets() {
            return sheets;
        }

        public void setSheets(Excel_Sheet[] sheets) {
            this.sheets = sheets;
        }
    }
}
