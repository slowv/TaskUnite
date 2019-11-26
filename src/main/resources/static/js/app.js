// Tùy biến thêm trường.
function Task(title, address, category, description){
  return {
    title: title, // String
    address: address, // String
    category: category, // id Category []
    description: description // String
  }
}
// Func call API
getListTask = function() {
  return [
    new Task('Task 1', 'Hà Nội', [1, 2, 3], 'Việc nhẹ lương cao!'),
    new Task('Task 2', 'Hồ Chí Minh', [1, 2, 3], 'Việc nhẹ lương cao!'),
  ];
};


