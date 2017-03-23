package cn.xyz.lcg.rocketmq.filter;

import com.alibaba.rocketmq.common.filter.FilterContext;
import com.alibaba.rocketmq.common.filter.MessageFilter;
import com.alibaba.rocketmq.common.message.MessageExt;

public class MessageFilterImpl implements MessageFilter {

	@Override
	public boolean match(MessageExt msg, FilterContext context) {
		String property = msg.getProperty("SequenceId");
		if (property != null) {
			int id = Integer.parseInt(property);
			if (((id % 3) == 0) /* && (id > 100) */) {
				return true;
			}
		}

		return false;
	}

}
