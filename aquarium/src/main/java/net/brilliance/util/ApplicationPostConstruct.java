/**
 * 
 */
package net.brilliance.util;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import net.brilliance.framework.logging.LogService;

/**
 * @author ducbq
 *
 */
@Component
public class ApplicationPostConstruct implements ApplicationListener<ApplicationReadyEvent> {
	@Inject 
	protected LogService cLogger;
	
	/*@Inject
	private BpmProcessEngine bpmProcessEngine;*/

	/*@Inject 
	private GlobalDataRepositoryManager globalDataRepositoryManager;*/

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("Fired on application ready event at: " + Calendar.getInstance().getTime());
	}

}
