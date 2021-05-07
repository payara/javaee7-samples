package org.javaee7.cdi.dynamic.interceptor.extension;

import jakarta.annotation.Priority;
import jakarta.interceptor.Interceptor;

/**
 * Class used to enable (activate) the dynamic interceptor and sets its priority
 * 
 * @author Arjan Tijms
 *
 */
@Interceptor
@Priority(200)
public class HelloInterceptorEnabler {

}