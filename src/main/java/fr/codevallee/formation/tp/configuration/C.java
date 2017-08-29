package fr.codevallee.formation.tp.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import fr.codevallee.formation.tp.service.DemonstrationServiceImpl;

/**
 * Classe permettant d'obtenir les objets à partir de Spring
 * 
 * @author ronan
 *
 */
@Component
public class C implements ApplicationContextAware {

	public static C i;

	private DemonstrationServiceImpl demonstrationServiceImpl = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		i = this;
		demonstrationServiceImpl = applicationContext.getBean(DemonstrationServiceImpl.class);
	}

	public DemonstrationServiceImpl getDemonstrationServiceImpl() {
		return demonstrationServiceImpl;
	}

}