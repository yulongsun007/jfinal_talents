package win.yulongsun.talents.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import win.yulongsun.talents.controller.*;
import win.yulongsun.talents.model._MappingKit;

public class MyConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.JSP);
//        me.setLogFactory(new Log4jLogFactory());
    }

    public void configRoute(Routes me) {
        me.add("/user", UserController.class);
        me.add("/plan", PlanController.class);
        me.add("/job_temp", JobTemplateController.class);
        me.add("/clazz", ClazzController.class);
        me.add("/resume", ResumeController.class);
        me.add("/resume_exper", ResumeExperController.class);
        me.add("/msg", MsgController.class);
        me.add("/user_plan_r", UserPlanRController.class);
        me.add("/user_plan_clazz_r", UserPlanClazzRController.class);
    }

    public void configPlugin(Plugins me) {
        loadPropertyFile("db_config.txt");
        //数据源
        DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
        me.add(dp);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        // 配置方言
        arp.setDialect(new MysqlDialect());
        // 配置Mapper
        _MappingKit.mapping(arp);
        me.add(arp);
        arp.setShowSql(true);
    }

    public void configInterceptor(Interceptors me) {
//        me.add(new Restful());
    }

    public void configHandler(Handlers me) {
    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8081, "/", 5);
    }
}