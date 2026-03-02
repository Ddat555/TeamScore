
const fontFamilySelect = document.getElementById('fontFamily');
const fontSizeInput = document.getElementById('fontSize');
const boldCheck = document.getElementById('bold');
const italicCheck = document.getElementById('italic');
const alignLeft = document.getElementById('alignLeft');
const alignCenter = document.getElementById('alignCenter');
const alignRight = document.getElementById('alignRight');

const rNum = document.getElementById('rNum');
const gNum = document.getElementById('gNum');
const bNum = document.getElementById('bNum');
const rRange = document.getElementById('rRange');
const gRange = document.getElementById('gRange');
const bRange = document.getElementById('bRange');
const colorPicker = document.getElementById('colorPicker');
const colorPreview = document.getElementById('colorPreview');

const userText = document.getElementById('userText');


function updateTextStyle() {
    userText.style.fontFamily = fontFamilySelect.value;

    userText.style.fontSize = fontSizeInput.value + 'pt';

    userText.style.fontWeight = boldCheck.checked ? 'bold' : 'normal';
    userText.style.fontStyle = italicCheck.checked ? 'italic' : 'normal';

    if (alignLeft.checked) userText.style.textAlign = 'left';
    else if (alignCenter.checked) userText.style.textAlign = 'center';
    else if (alignRight.checked) userText.style.textAlign = 'right';

}

function updateColorFromRGB() {
    let r = Math.min(255, Math.max(0, Number(rNum.value) || 0));
    let g = Math.min(255, Math.max(0, Number(gNum.value) || 0));
    let b = Math.min(255, Math.max(0, Number(bNum.value) || 0));

    rRange.value = r;
    gRange.value = g;
    bRange.value = b;
    
    const hex = '#' + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
    colorPicker.value = hex;
    
    const color = `rgb(${r}, ${g}, ${b})`;
    userText.style.color = color;
    colorPreview.style.backgroundColor = color;
}

function updateColorFromRange() {
    let r = Number(rRange.value);
    let g = Number(gRange.value);
    let b = Number(bRange.value);
    
    rNum.value = r;
    gNum.value = g;
    bNum.value = b;
    
    const hex = '#' + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
    colorPicker.value = hex;
    
    const color = `rgb(${r}, ${g}, ${b})`;
    userText.style.color = color;
    colorPreview.style.backgroundColor = color;
}

function updateColorFromPicker() {
    const hex = colorPicker.value;
    
    const r = parseInt(hex.slice(1, 3), 16);
    const g = parseInt(hex.slice(3, 5), 16);
    const b = parseInt(hex.slice(5, 7), 16);
    
    rNum.value = r;
    gNum.value = g;
    bNum.value = b;
    rRange.value = r;
    gRange.value = g;
    bRange.value = b;
    
    userText.style.color = hex;
    colorPreview.style.backgroundColor = hex;
}

fontFamilySelect.addEventListener('input', updateTextStyle);
fontSizeInput.addEventListener('input', updateTextStyle);
boldCheck.addEventListener('change', updateTextStyle);
italicCheck.addEventListener('change', updateTextStyle);
alignLeft.addEventListener('change', updateTextStyle);
alignCenter.addEventListener('change', updateTextStyle);
alignRight.addEventListener('change', updateTextStyle);

rNum.addEventListener('input', updateColorFromRGB);
gNum.addEventListener('input', updateColorFromRGB);
bNum.addEventListener('input', updateColorFromRGB);

rRange.addEventListener('input', updateColorFromRange);
gRange.addEventListener('input', updateColorFromRange);
bRange.addEventListener('input', updateColorFromRange);

colorPicker.addEventListener('input', updateColorFromPicker);

updateTextStyle();
updateColorFromRGB();