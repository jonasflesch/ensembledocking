package br.com.jonasflesch.ensembledocking.serialization;

import br.com.jonasflesch.ensembledocking.model.Mdp;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by jonasflesch on 4/26/15.
 */
@Component
public class MdpSerializer {

	private static final Logger LOGGER = Logger.getLogger(MdpSerializer.class);

	public void serialize(final Mdp mdp, final OutputStream outputStream){
		try {
			for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(Mdp.class, Object.class).getPropertyDescriptors()){
				Object value = propertyDescriptor.getReadMethod().invoke(mdp);
				if(value != null){
					String line = propertyDescriptor.getName() + " = " + valueToString(value) + System.getProperty("line.separator");
					outputStream.write(line.getBytes(Charset.forName("UTF-8")));
				}
			}
		} catch (Exception e){
			LOGGER.error("Error serializing mdp", e);
		}
	}

	private String valueToString(Object value){
		if(String.class.equals(value.getClass())){
			return (String) value;
		} else if(Double.class.equals(value.getClass())){
			return value.toString();
		} else if(Integer.class.equals(value.getClass())){
			return value.toString();
		} else if(String[].class.equals(value.getClass())){
			StringBuilder sb = new StringBuilder();
			for(String string : (String[])value){
				sb.append(string).append(' ');
			}
			return sb.toString();
		} else if(Double[].class.equals(value.getClass())){
			StringBuilder sb = new StringBuilder();
			for(Double db : (Double[])value){
				sb.append(db.toString()).append(' ');
			}
			return sb.toString();
		} else if(Integer[].class.equals(value.getClass())){
			StringBuilder sb = new StringBuilder();
			for(Integer integer : (Integer[])value){
				sb.append(integer.toString()).append(' ');
			}
			return sb.toString();
		}
		return null;
	}


}
