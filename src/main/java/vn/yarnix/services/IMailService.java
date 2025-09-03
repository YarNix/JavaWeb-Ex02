package vn.yarnix.services;

public interface IMailService {
	void send(String to, String subject, String body);
}
