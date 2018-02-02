package config;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;
import com.baidu.disconf.client.addons.properties.ReloadingPropertyPlaceholderConfigurer;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * disconf 集成配置类
 *
 * @author xiangzhurui
 * @version 2018/2/2
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class DisconfintegratedConfig {

    @Bean(destroyMethod = "destroy")
    public DisconfMgrBean disconfMgrBean() {
        DisconfMgrBean disconfMgrBean = new DisconfMgrBean();

        disconfMgrBean.setScanPackage("com.example.disconf");

        return disconfMgrBean;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DisconfMgrBeanSecond disconfMgrBeanSecond() {
        DisconfMgrBeanSecond disconfMgrBeanSecond = new DisconfMgrBeanSecond();

        return disconfMgrBeanSecond;
    }

    @Bean
    public ReloadablePropertiesFactoryBean disconfReloadablePropertiesFactoryBean() {
        ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean = new ReloadablePropertiesFactoryBean();

        List<String> fileNames = Lists.newArrayList(
                "classpath*:autoconfig.properties",
                "classpath*:simple.properties"
        );
        reloadablePropertiesFactoryBean.setLocations(fileNames);

        return reloadablePropertiesFactoryBean;
    }

    @Bean
    public ReloadingPropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        ReloadingPropertyPlaceholderConfigurer placeholderConfigurer = new ReloadingPropertyPlaceholderConfigurer();

        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        placeholderConfigurer.setIgnoreResourceNotFound(true);

        Properties[] propertiesArray = new Properties[]{disconfReloadablePropertiesFactoryBean().getObject()};

        placeholderConfigurer.setPropertiesArray(propertiesArray);

        return placeholderConfigurer;
    }


}
