package win.yulongsun.talents.model;

import win.yulongsun.talents.model.base.BaseClazz;

import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Clazz extends BaseClazz<Clazz> {
	public static final Clazz dao = new Clazz();

    public List<Clazz> findByPlanId(Integer planId) {
        return find("select * from t_clazz where plan_id=? order by clazz_priority asc",planId);
    }
}
