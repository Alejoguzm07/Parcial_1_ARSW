package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuidFinderDesktopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuidFinderDesktopApplication.class, args);
		
		try {
			GuidFinder finder= new GuidFinder();
			//System.out.println(finder.countGuids(UUID.fromString("d0692660-c39a-4d73-9496-d9df0c4ebdf3")));
                        System.out.println(finder.timeCountGuids(UUID.fromString("d0692660-c39a-4d73-9496-d9df0c4ebdf3")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
