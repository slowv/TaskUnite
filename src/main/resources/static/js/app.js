let task = {
  title: null, // String
  address: null, // String
  category: [], // id Category
  description: null // String
};

function Task(title, address, category, description){
  return {
    title: title, // String
    address: address, // String
    category: category, // id Category []
    description: description // String
  }
}

let API = {
  getListTask: getListTask() // Ví dụ cho việc gọi Api tự tùy biến thêm theo từng mục đích!
};

// Func call API
function getListTask() {
  return [
    new Task('Task 1', 'Hà Nội', [1, 2, 3], 'Việc nhẹ lương cao!'),
    new Task('Task 2', 'Hồ Chí Minh', [1, 2, 3], 'Việc nhẹ lương cao!'),
  ];
}
