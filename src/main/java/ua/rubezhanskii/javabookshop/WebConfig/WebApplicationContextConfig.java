package ua.rubezhanskii.javabookshop.WebConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import ua.rubezhanskii.javabookshop.reports.ExcelView;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebMvc
@ComponentScan("ua.rubezhanskii.javabookshop")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter  {




    @Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

   @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
       List<HttpMessageConverter<?>> converters=restTemplate.getMessageConverters();
       converters.add(converter);
       restTemplate.setMessageConverters(converters);
        return restTemplate;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
        registry.enableContentNegotiation(new ExcelView());

    }

  /*  @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    List<HttpMessageConverter<?>>messageConverters=new ArrayList<>();
            messageConverters.add(new MappingJackson2HttpMessageConverter());
            super.configureMessageConverters(messageConverters);
    }*/

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useJaf(false)
                .ignoreAcceptHeader(true)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .defaultContentType(MediaType.TEXT_HTML)
                .parameterName("type")
                .favorParameter(true)
                .ignoreUnknownPathExtensions(false)
                .ignoreAcceptHeader(false);

    }




    @Bean
    public InternalResourceViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return  resolver;

    }

    @Bean
    public ViewResolver viewResolver(ContentNegotiationManager manager){
        ContentNegotiatingViewResolver resolver=new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        ArrayList<View> views=new ArrayList<View>();
        views.add(jsonView());
        resolver.setDefaultViews(views);
        return  resolver;
    }


}

 /*   @Bean
    public MarshallingView xmlView() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Book.class, Cart.class, Customer.class);

        MarshallingView xmlView = new MarshallingView(marshaller);
        return xmlView;
    }*/



  /*  @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
       // resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }*/

  /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProcessingTimeLogInterceptor());

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);

        registry.addInterceptor(promoCodeInterceptor())
                .addPathPatterns();
    }*/



/*    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));

        return resolver;
    }*/

  /* @Bean
    public HandlerInterceptor promoCodeInterceptor() {
        PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
        promoCodeInterceptor.setPromoCode("OFF3R");
        promoCodeInterceptor.setOfferRedirect("market/products");
        promoCodeInterceptor.setErrorRedirect("invalidPromoCode");

        return promoCodeInterceptor;
    }*/

 /*   @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator(){
        return validator();
    }
}*/

  /*  @Bean
    public ProductValidator productValidator () {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UnitsInStockValidator());

        ProductValidator productValidator = new ProductValidator();
        productValidator.setSpringValidators(springValidators);

        return productValidator;
    }*/

