
let tasks = {
    todo: [],
    inprogress: [],
    done: []
};


let draggedItem = null;
let draggedIndex = null;
let draggedColumn = null;


function addTask() {
    const inputText = document.getElementById('taskInput').value;
    const inputSelect = document.getElementById('columnSelect').value;
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
}

function deleteTask(taskIndex, columnId) {
    tasks[columnId] = tasks[columnId].filter((_, index) => index !== taskIndex);
    renderColumn(columnId);
    saveToStorage();
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
}


function clearColumn(columnId) {
    tasks[columnId] = [];
    renderColumn(columnId);
    saveToStorage();
}


function renderAllTasks() {
    renderColumn('todo');
    renderColumn('inprogress');
    renderColumn('done');
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
    taskDiv.setAttribute('data-index', index);
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
    taskDiv.addEventListener('dragend', handleDragEnd);
    
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
}



function handleDragStart(e) {
    const taskDiv = e.target.closest('.task-item');
    if (!taskDiv) return;
    
    draggedItem = taskDiv;
    draggedIndex = taskDiv.getAttribute('data-index');
    draggedColumn = taskDiv.getAttribute('data-column');
    
    taskDiv.classList.add('dragging');
    
    e.dataTransfer.setData('text/plain', '');
    e.dataTransfer.effectAllowed = 'move';
    
}

function handleDragOver(e) {
    e.preventDefault();
    e.dataTransfer.dropEffect = 'move';
}

function handleDrop(e) {
    e.preventDefault();
    
    const targetDiv = e.target.closest('.task-item');
    if (!targetDiv) return;
    
    const targetIndex = targetDiv.getAttribute('data-index');
    const targetColumn = targetDiv.getAttribute('data-column');
    
    if (draggedIndex === null || draggedColumn === null) {
        return;
    }
    
    if (draggedColumn !== targetColumn) {
        return;
    }
    
    if (draggedIndex === targetIndex) {
        return;
    }
    
    
    moveTask(parseInt(draggedIndex), parseInt(targetIndex), draggedColumn);
    
    if (draggedItem) {
        draggedItem.classList.remove('dragging');
    }
    
    draggedItem = null;
    draggedIndex = null;
    draggedColumn = null;
}

function handleDragEnd(e) {
    const taskDiv = e.target.closest('.task-item');
    if (taskDiv) {
        taskDiv.classList.remove('dragging');
    }
    
    draggedItem = null;
    draggedIndex = null;
    draggedColumn = null;
    
}

function moveTask(fromIndex, toIndex, columnId) {
    const column = tasks[columnId];
    
    const [movedTask] = column.splice(fromIndex, 1);
    column.splice(toIndex, 0, movedTask);
    
    renderColumn(columnId);
    saveToStorage();
}


document.addEventListener('DOMContentLoaded', function() {
    loadFromStorage();
});