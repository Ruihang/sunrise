package net.brilliance.service.api.admin.quartz;

import net.brilliance.domain.entity.schedule.JobDefinition;
import net.brilliance.exceptions.ObjectNotFoundException;
import net.brilliance.framework.service.GenericService;

public interface JobDefinitionService extends GenericService<JobDefinition, Long> {

	/**
	 * Get one JobDefinition with the provided name.
	 * 
	 * @param name
	 *            The JobDefinition name
	 * @return The JobDefinition
	 * @throws ObjectNotFoundException
	 *             If no such JobDefinition exists.
	 */
	JobDefinition getOne(String name) throws ObjectNotFoundException;
}
