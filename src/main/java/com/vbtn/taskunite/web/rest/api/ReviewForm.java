package com.vbtn.taskunite.web.rest.api;

import com.vbtn.taskunite.domain.Task;
import com.vbtn.taskunite.domain.UserInformation;

public class ReviewForm {

	public static class Create {

		private String content;
		private Double point;
		private Task task;
		private UserInformation user;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Double getPoint() {
			return point;
		}

		public void setPoint(Double point) {
			this.point = point;
		}

		public Task getTask() {
			return task;
		}

		public void setTask(Task task) {
			this.task = task;
		}

		public UserInformation getUser() {
			return user;
		}

		public void setUser(UserInformation user) {
			this.user = user;
		}

	}

}
