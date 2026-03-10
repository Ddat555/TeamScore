
let tasks = {
    todo: [],
    inprogress: [],
    done: []
};


function addTask() {
    const inputText = document.getElementById('taskInput').value;
    const inputSelect = document.getElementById('columnSelect').value;
    console.log('название задачи ' + inputText)
    console.log('статус задачи ' + inputSelect)
    switch(inputSelect){
        case 'inprogress':
            tasks.inprogress.push({inputText,inputSelect})
            renderColumn('inprogress');
            break;

        case 'todo':
            tasks.todo.push({inputText,inputSelect})
            renderColumn('todo');
            break;

        case 'done':
            tasks.done.push({inputText,inputSelect})
            renderColumn('done');
            break;
    }
    console.log(tasks);
    saveToStorage();
}

function editTask(taskIndex, columnId) {
    const task = tasks[columnId][taskIndex];
    
    const newText = prompt('Редактировать задачу:', task.inputText);
    
    if (newText !== null && newText.trim() !== '') {
        task.inputText = newText;
        renderColumn(columnId);
        saveToStorage();
    }
    
    console.log('editTask() вызвана для задачи', taskIndex, 'в колонке', columnId);
}

function deleteTask(taskIndex, columnId) {
    tasks[columnId] = tasks[columnId].filter((_, index) => index !== taskIndex);
    renderColumn(columnId);
    saveToStorage();
    console.log('deleteTask() вызвана для задачи', taskIndex, 'в колонке', columnId);
}


function moveUp(taskId, columnId) {
    if (taskId === 0) return;
    const column = tasks[columnId];
    [column[taskId], column[taskId - 1]] = [column[taskId - 1], column[taskId]];
    
    renderColumn(columnId);
    saveToStorage();
}

function moveDown(taskId, columnId) {
    const column = tasks[columnId];
    if (taskId === column.length - 1) return;
    [column[taskId], column[taskId + 1]] = [column[taskId + 1], column[taskId]];

    renderColumn(columnId);
    saveToStorage();
}

function moveLeft(taskId, columnId) {
    let prevColumn;
    
    if (columnId === 'inprogress') prevColumn = 'todo';
    else if (columnId === 'done') prevColumn = 'inprogress';
    else return;
    
    const task = tasks[columnId][taskId];
    
    deleteTask(taskId, columnId);
    tasks[prevColumn].push(task);
    
    renderAllTasks();
    saveToStorage();
    console.log('moveLeft() из', columnId, 'в', prevColumn);
}

function moveRight(taskId, columnId) {
    let nextColumn;
    
    if (columnId === 'todo') nextColumn = 'inprogress';
    else if (columnId === 'inprogress') nextColumn = 'done';
    else return;
    
    const task = tasks[columnId][taskId];
    
    deleteTask(taskId, columnId);
    tasks[nextColumn].push(task);
    
    renderAllTasks();
    saveToStorage();
    console.log('moveRight() из', columnId, 'в', nextColumn);
}


function clearColumn(columnId) {
    tasks[columnId] = [];
    renderColumn(columnId);
    saveToStorage();
    console.log('clearColumn() вызвана для колонки', columnId);
}


function renderAllTasks() {
    renderColumn('todo');
    renderColumn('inprogress');
    renderColumn('done');
    console.log('renderAllTasks() вызвана');
}


function renderColumn(columnId) {
    const container = document.getElementById(columnId + 'List');
    if (!container) return;
    
    container.innerHTML = '';
    const columnTasks = tasks[columnId];
    
    columnTasks.forEach((task, index) => {
        const taskElement = createTaskElement(task, columnId, index);
        container.appendChild(taskElement);
    });
    
    updateCounter(columnId);
}

function createTaskElement(task, columnId, index) {
    const taskDiv = document.createElement('div');
    taskDiv.className = 'task-item list-group-item list-group-item-action p-3 mb-2 rounded';
    taskDiv.setAttribute('data-id', index);
    taskDiv.setAttribute('data-column', columnId);
    
    taskDiv.innerHTML = `
        <div class="d-flex justify-content-between align-items-center">
            <span class="task-text" onclick="editTask(${index}, '${columnId}')">${task.inputText}</span>
            <div class="task-actions btn-group btn-group-sm">
                <button class="btn btn-outline-primary" onclick="moveUp(${index}, '${columnId}')" title="Вверх">
                    <i class="bi bi-arrow-up"></i>
                </button>
                <button class="btn btn-outline-primary" onclick="moveDown(${index}, '${columnId}')" title="Вниз">
                    <i class="bi bi-arrow-down"></i>
                </button>
                <button class="btn btn-outline-secondary" onclick="moveLeft(${index}, '${columnId}')" title="Влево">
                    <i class="bi bi-arrow-left"></i>
                </button>
                <button class="btn btn-outline-secondary" onclick="moveRight(${index}, '${columnId}')" title="Вправо">
                    <i class="bi bi-arrow-right"></i>
                </button>
                <button class="btn btn-outline-danger" onclick="deleteTask(${index}, '${columnId}')" title="Удалить">
                    <i class="bi bi-trash"></i>
                </button>
            </div>
        </div>
    `;
    
    taskDiv.setAttribute('draggable', 'true');
    taskDiv.addEventListener('dragstart', handleDragStart);
    taskDiv.addEventListener('dragover', handleDragOver);
    taskDiv.addEventListener('drop', handleDrop);
    
    return taskDiv;
}


function updateCounter(columnId) {
    const counterElement = document.getElementById(columnId + 'Count');
    if (counterElement) {
        counterElement.textContent = tasks[columnId].length;
    }
}


function saveToStorage() {
    const tasksJSON = JSON.stringify(tasks);
    localStorage.setItem('tasks', tasksJSON);
    console.log('saveToStorage() вызвана');
}

function loadFromStorage() {
    const tasksJSON = localStorage.getItem('tasks');
    
    if (tasksJSON) {
        tasks = JSON.parse(tasksJSON);
    } else {
        tasks = {
            todo: [],
            inprogress: [],
            done: []
        };
    }
    
    renderAllTasks();
    console.log('loadFromStorage() вызвана');
}


function handleDragStart(e) {
    console.log('handleDragStart() вызвана');
}

function handleDragOver(e) {
    e.preventDefault();
}

function handleDrop(e) {
    console.log('handleDrop() вызвана');
}


document.addEventListener('DOMContentLoaded', function() {
    console.log('Страница загружена, инициализация...');
    loadFromStorage();
});