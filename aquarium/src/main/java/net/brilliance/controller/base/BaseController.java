/**
 * 
 */
package net.brilliance.controller.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.brilliance.controller.controller.constants.ControllerConstants;
import net.brilliance.framework.logging.LogService;
import net.brilliance.framework.model.SearchParameter;
import net.brilliance.manager.catalog.CategoryManager;
import net.brilliance.manager.catalog.impl.DepartmentManager;
import net.brilliance.manager.configuration.ConfigurationManager;
import net.brilliance.model.SelectItem;
import net.brilliance.service.api.inventory.CatalogueSubtypeService;

/**
 * @author ducbq
 *
 */
@SuppressWarnings("rawtypes")
public abstract class BaseController {
	protected static final String PAGE_POSTFIX_BROWSE = "Browse";
	protected static final String PAGE_POSTFIX_SHOW = "Show";
	protected static final String PAGE_POSTFIX_EDIT = "Edit";

	protected static final String REDIRECT = "redirect:/";

	@Inject
	protected LogService log;

	@Inject
	protected DepartmentManager departmentServiceManager;

	@Inject
	protected CategoryManager categoryServiceManager;

	@Inject
	protected MessageSource messageSource;

	@Inject
	private ConfigurationManager configurationManager;

	@Inject
	private CatalogueSubtypeService catalogueSubtypeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	protected List<SelectItem> buildSelectedItems(List<?> objects) {
		List<SelectItem> selectItems = new ArrayList<>();
		for (Object object : objects) {
			selectItems.add(this.buildSelectableObject(object));
		}
		return selectItems;
	}

	protected SelectItem buildSelectableObject(Object beanObject) {
		return null;
	}

	protected String buildBaseURL(ServletRequest request) {
		String baseUrl = String.format("%s://%s:%d", request.getScheme(), request.getServerName(),
				request.getServerPort());
		return baseUrl;
	}

	protected HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attr.getRequest().getSession();
	}

	protected HttpServletRequest getCurrentHttpRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			return request;
		}

		log.debug("Not called in the context of an HTTP request");
		return null;
	}

	protected boolean isContinueOther(Model model, HttpServletRequest request) {
		boolean isContinuedOther = false;
		isContinuedOther = "on".equalsIgnoreCase(request.getParameter(ControllerConstants.PARAM_CREATE_OTHER));
		if (true == isContinuedOther) {
			request.getSession().setAttribute(ControllerConstants.PARAM_CREATE_OTHER, Boolean.TRUE);
		}
		return isContinuedOther;
	}

	/**
	 * Import business objects.
	 */
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String imports(Model model, HttpServletRequest request) {
		log.info("Importing business objects .....");
		String importResults = performImport(model, request);
		log.info("Leave importing business objects!");
		return importResults;
	}

	protected String performImport(Model model, HttpServletRequest request) {
		return ControllerConstants.DEAULT_PAGE_CONTEXT_PREFIX;
	}

	/**
	 * Export business objects.
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String exports(Model model, HttpServletRequest request) {
		log.info("Exporting business objects .....");
		String exportResults = performExport(model, request);
		log.info("Leaving exporting business objects .....");
		return exportResults;
	}

	protected String performExport(Model model, HttpServletRequest request) {
		return ControllerConstants.DEAULT_PAGE_CONTEXT_PREFIX;
	}

	protected String buildRedirectShowBizObjectRoute(String controllerId, Long businessObjectId) {
		return new StringBuilder(ControllerConstants.REDIRECT_PREFIX).append(controllerId).append("/")
				.append(businessObjectId).toString();
	}
}