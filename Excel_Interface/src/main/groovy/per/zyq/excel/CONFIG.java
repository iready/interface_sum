package per.zyq.excel;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.render.ViewType;
import org.apache.log4j.BasicConfigurator;
import per.zyq.excel.controller.index;

/**
 * Created by Administrator on 2016/4/23.
 */
public class CONFIG extends JFinalConfig {
    public static void main(String[] args) {
        JFinal.start("D:\\JAVA\\workspace\\mine\\interface_sum\\out\\artifacts\\Excel_Interface\\exploded\\Excel_Interface-0.1.war", 82, "/", 5);
    }

    @Override
    public void configConstant(Constants me) {
        me.setEncoding("utf-8");
        me.setDevMode(false);
        me.setViewType(ViewType.JSP);
        me.setBaseViewPath("/WEB-INF");
        BasicConfigurator.configure();
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", index.class);
    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {
    }
}
