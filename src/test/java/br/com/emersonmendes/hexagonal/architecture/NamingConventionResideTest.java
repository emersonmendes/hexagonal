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
             "..adapters.in.controller.mapper..",
                "..adapters.out.repository.mapper..",
                "..adapters.out.client.mapper.."
            );

    @ArchTest
    public static final ArchRule controllerResideOnlyControllerPackage = classes()
        .that()
        .haveNameMatching(".*Controller")
        .should()
        .resideInAPackage("..adapters.in.controller..");

    @ArchTest
    public static final ArchRule adapterResideOnlyAdapterPackage = classes()
        .that()
        .haveNameMatching(".*Adapter")
        .should()
        .resideInAPackage("..hexagonal.adapters.out..");

    @ArchTest
    public static final ArchRule portsResideOnlyPortsPackage = classes()
        .that()
        .haveNameMatching(".*Port")
        .should()
        .resideInAPackage("..hexagonal.application.ports..");

    @ArchTest
    public static final ArchRule useCasesResideOnlyUseCasesPackage = classes()
        .that()
        .haveNameMatching(".*UseCase")
        .should()
        .resideInAPackage("..hexagonal.application.core.usecase..");

}
