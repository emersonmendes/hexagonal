package br.com.emersonmendes.hexagonal.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.com.emersonmendes.hexagonal")
public class NamingConventionResideTest {

    @ArchTest
    public static final ArchRule mapperResideOnlyMapperPackage = classes()
        .that()
        .haveNameMatching(".*Mapper")
            .should()
            .resideInAnyPackage(
             "..adapter.in..mapper..",
                "..adapter.out..mapper..",
                "..adapter.out.repository.mapper.."
            );

    @ArchTest
    public static final ArchRule controllerResideOnlyWebPackage = classes()
        .that()
        .haveNameMatching(".*Controller")
        .should()
        .resideInAPackage("..adapter.in.web..");

    @ArchTest
    public static final ArchRule consumerResideOnlyMessagingPackage = classes()
        .that()
        .haveNameMatching(".*Consumer")
        .should()
        .resideInAPackage("..adapter.in.messaging..");

    @ArchTest
    public static final ArchRule adapterResideOnlyAdapterPackage = classes()
        .that()
        .haveNameMatching(".*Adapter")
        .should()
        .resideInAPackage("..adapter.out..");

    @ArchTest
    public static final ArchRule portsResideOnlyPortsPackage = classes()
        .that()
        .haveNameMatching(".*Port")
        .should()
        .resideInAPackage("..application.ports..");

    @ArchTest
    public static final ArchRule useCasesResideOnlyUseCasesPackage = classes()
        .that()
        .haveNameMatching(".*UseCase")
        .should()
        .resideInAPackage("..application.core.usecase..");

}
