package win.yulongsun.talents.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("t_clazz", "clazz_id", Clazz.class);
		arp.addMapping("t_job_template", "tmp_id", JobTemplate.class);
		arp.addMapping("t_msg", "msg_id", Msg.class);
		arp.addMapping("t_plan", "plan_id", Plan.class);
		arp.addMapping("t_plan_clazz_r", "_id", PlanClazzR.class);
		arp.addMapping("t_resume", "resume_id", Resume.class);
		arp.addMapping("t_resume_exper", "_id", ResumeExper.class);
		arp.addMapping("t_role", "_id", Role.class);
		arp.addMapping("t_user", "user_id", User.class);
		arp.addMapping("t_user_company_r", "company_id", UserCompanyR.class);
		arp.addMapping("t_user_plan_r", "_id", UserPlanR.class);
	}
}

