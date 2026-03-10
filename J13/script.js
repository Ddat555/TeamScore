
let tasks = {
    todo: [],
    inprogress: [],
    done: []
};

function addTask() {
    console.log('addTask() вызвана');
}

function editTask(taskId, columnId) {
    console.log('editTask() вызвана для задачи', taskId, 'в колонке', columnId);
}

function deleteTask(taskId, columnId) {
    console.log('deleteTask() вызвана для задачи', taskId, 'в колонке', columnId);
}


function moveUp(taskId, columnId) {
    console.log('moveUp() вызвана для задачи', taskId, 'в колонке', columnId);
}

function moveDown(taskId, columnId) {
    console.log('moveDown() вызвана для задачи', taskId, 'в колонке', columnId);
}

function moveLeft(taskId, columnId) {
    console.log('moveLeft() вызвана для задачи', taskId, 'из колонки', columnId);
}

function moveRight(taskId, columnId) {

    console.log('moveRight() вызвана для задачи', taskId, 'из колонки', columnId);
}


function clearColumn(columnId) {

    console.log('clearColumn() вызвана для колонки', columnId);
}


function renderAllTasks() {

    console.log('renderAllTasks() вызвана');
}


function renderColumn(columnId) {
    console.log('renderColumn() вызвана для', columnId);
}

function createTaskElement(task, columnId) {
    console.log('createTaskElement() вызвана для задачи', task);
    return document.createElement('div');
}


function updateCounters() {
    console.log('updateCounters() вызвана');
}


function saveToStorage() {

    console.log('saveToStorage() вызвана');
}

function loadFromStorage() {

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