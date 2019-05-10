package cn.echocow.generate.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* user
*
* @author echo
* @date 2019-5-10 0:53:28
*/
@Data
@Table(name = "user")
@Entity(name = "user")
public class User implements Serializable {
FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> columns  [in template "entity.ftl" at line 19, column 8]

----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: #list columns as column  [in template "entity.ftl" at line 19, column 1]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.Expression.assertNonNull(Expression.java:233)
	at freemarker.core.IteratorBlock.acceptWithResult(IteratorBlock.java:103)
	at freemarker.core.IteratorBlock.accept(IteratorBlock.java:93)
	at freemarker.core.Environment.visit(Environment.java:330)
	at freemarker.core.Environment.visit(Environment.java:336)
	at freemarker.core.Environment.process(Environment.java:309)
	at freemarker.template.Template.process(Template.java:384)
	at cn.echocow.gendemo.GenEntity.run(GenEntity.java:49)
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:804)
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:794)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:324)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1260)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1248)
	at cn.echocow.gendemo.GenDemoApplication.main(GenDemoApplication.java:10)
