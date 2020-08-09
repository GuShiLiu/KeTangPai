package mybatis.demo;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("input password:");
			String password = scanner.nextLine();
			String ipassword = bCryptPasswordEncoder.encode(password);
			System.out.println("生成的密文"+ipassword);
			String a;
			System.out.print("input password again:");
			a = scanner.nextLine();
			System.out.println("生成的密文"+bCryptPasswordEncoder.encode(a));
			System.out.println("两次密码是否相同？"+bCryptPasswordEncoder.matches(a,ipassword));
		}
	}

}
