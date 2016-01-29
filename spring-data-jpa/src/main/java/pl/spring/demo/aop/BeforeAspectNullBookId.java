package pl.spring.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;

@Component
@Aspect
public class BeforeAspectNullBookId {

	@Autowired
	Sequence sequence;

}
