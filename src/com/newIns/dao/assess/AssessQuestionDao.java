/**
 * 
 */
package com.newIns.dao.assess;

import java.util.List;

import com.newIns.model.assess.AssessQuestionWithOption;

/**@Description  加载问题完整信息Dao
 * @author Guan
 * @time 2016年6月29日 下午4:20:55
 */

public interface AssessQuestionDao {

	List<AssessQuestionWithOption> loadAssessQuestionWithOption(int aqnId);
}
