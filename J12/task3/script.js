const oilPrices = [
    78.45,
    78.45,
    78.89,
    80.23,
    1.45,
    80.67,
    79.98,
    80.34,
    1.02,
    80.56,
    79.87,
    78.94,
    79.45,
    80.11,
    81.33,
    82.01,
    81.67,
    80.89,
    80.23,
    79.56,
    78.89,
    79.34,
    80.12,
    79.78,
    78.45,
    77.89,
    78.34,
    79.01,
    78.67
];

function formatNumber(value, digits = 2) {
    return value.toFixed(digits);
}

function getArrow(change) {
    if (change > 0) return '<span class="arrow-up">↑</span>';
    if (change < 0) return '<span class="arrow-down">↓</span>';
    return '−';
}

function geometricMean(values) {
    let product = 1;
    for (let i = 0; i < values.length; i++) {
        product *= values[i];
    }
    return Math.pow(product, 1 / values.length);
}

function createTable() {
    const n = oilPrices.length;
    
    const priceChanges = [];
    const chainIndices = [];
    const growthRates = [];
    
    for (let i = 1; i < n; i++) {
        const change = oilPrices[i] - oilPrices[i-1];
        priceChanges.push(change);
        
        const index = oilPrices[i] / oilPrices[i-1];
        chainIndices.push(index);
        
        growthRates.push(index - 1);
    }
    
    const maxPrice = Math.max(...oilPrices);
    const minPrice = Math.min(...oilPrices);
    
    const avgChange = priceChanges.reduce((sum, val) => sum + val, 0) / priceChanges.length;
    const geoMeanIndex = geometricMean(chainIndices);
    
    const positiveCount = priceChanges.filter(change => change > 0).length;
    const negativeCount = priceChanges.filter(change => change < 0).length;
    
    let html = `
        <table>
            <thead>
                <tr>
                    <th>День (t)</th>
                    <th>Цена нефти (ЦН<sub>t</sub>)</th>
                    <th>Прирост цены (∆ЦН<sub>t</sub>)</th>
                    <th>Цепной индекс (I<sub>t</sub><sup>ЦН</sup>)</th>
                    <th>Темп прироста (∆I<sub>t</sub><sup>цена</sup>)</th>
                    <th>Тренд</th>
                </tr>
            </thead>
            <tbody>
    `;
    
    for (let i = 0; i < n; i++) {
        let row = '<tr>';
        row += `<td>${i + 1}</td>`;
        row += `<td>${formatNumber(oilPrices[i])}</td>`;
        
        if (i === 0) {
            row += '<td>—</td><td>—</td><td>—</td><td>—</td>';
        } else {
            const change = priceChanges[i-1];
            const index = chainIndices[i-1];
            const rate = growthRates[i-1];
            
            row += `<td>${formatNumber(change)}</td>`;
            row += `<td>${formatNumber(index, 3)}</td>`; 
            row += `<td>${formatNumber(rate * 100)}%</td>`;
            row += `<td>${getArrow(change)}</td>`;
        }
        
        row += '</tr>';
        html += row;
    }
    
    html += `
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="6" style="text-align: left;">
                        Максимальная цена: <b>${formatNumber(maxPrice)}</b> | 
                        Минимальная цена: <b>${formatNumber(minPrice)}</b> | 
                        Среднее арифметическое приростов: <b>${formatNumber(avgChange)}</b> | 
                        Среднее геометрическое цепных индексов: <b>${formatNumber(geoMeanIndex, 3)}</b> | 
                        Положительных приростов: <b>${positiveCount}</b> | 
                        Отрицательных приростов: <b>${negativeCount}</b>
                    </td>
                </tr>
            </tfoot>
        </table>
    `;
    
    document.getElementById('tableContainer').innerHTML = html;
}

window.onload = createTable;