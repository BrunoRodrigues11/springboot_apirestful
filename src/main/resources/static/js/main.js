function fetchClientes(){
    console.log("Chamou fetch clientes")
    // Faz uma solicitação GET para a API
    fetch('/clientes')
    .then(response => response.json())
    .then(data => {
        // Manipula os dados recebidos para criar as linhas da tabela
        const tableBody = document.getElementById('table-body');
        
        data.forEach(cliente => {
            const row = document.createElement('tr');
            
            const idCell = document.createElement('td');
            const nomeCell = document.createElement('td');
            const enderecolCell = document.createElement('td');
            const telefoneCell = document.createElement('td');
            const estadoCivilCell = document.createElement('td');                
            
            idCell.textContent = cliente.idCliente;
            nomeCell.textContent = cliente.nome;
            enderecolCell.textContent = cliente.endereco;
            telefoneCell.textContent = cliente.fone;
            estadoCivilCell.textContent = cliente.estadoCivil;                
            
            row.appendChild(idCell);
            row.appendChild(nomeCell);
            row.appendChild(enderecolCell);
            row.appendChild(telefoneCell);
            row.appendChild(estadoCivilCell);       

            row.setAttribute('data-cliente-id', cliente.idCliente);

            row.addEventListener('click', () => {
                // Chame uma função para lidar com a seleção da linha
                handleRowSelection(cliente.idCliente);
            });

            tableBody.appendChild(row);
        });
    });
}

let selectedClientId = null;

function handleRowSelection(clienteId) {
    selectedClientId = clienteId;
}

function toggleRowColor(row) {
    $(row).toggleClass('selected');         
}
  
$(document).ready(function() {
  // Variável para armazenar a linha selecionada atualmente
  var linhaSelecionada = null;

  // Adicione um evento de clique nas linhas da tabela
  $('table tbody').on('click', 'tr', function() {
    // Remove a classe 'selected' da linha anteriormente selecionada (se houver)
    if (linhaSelecionada !== null) {
      $(linhaSelecionada).removeClass('selected');
    }

    // Adiciona a classe 'selected' à linha clicada
    $(this).addClass('selected');

    // Armazena a referência da linha selecionada
    linhaSelecionada = this;
  });
    
});

const editarButton = document.getElementById('visualizar-button');
editarButton.addEventListener('click', () => {
  if (selectedClientId) {
    // Chame uma função para abrir o modal com os dados do usuário selecionado
    openModal(selectedClientId);
  } else {
    // Exiba uma mensagem de erro ou tome alguma outra ação, caso nenhum cliente esteja selecionado
    console.error('Nenhum cliente selecionado');
  }
});

function fetchClienteById(clienteId) {
    fetch(`/clientes/${clienteId}`)
      .then(response => response.json())
      .then(data => {
        const nomeElement = document.getElementById('modal-nome');
        const enderecoElement = document.getElementById('modal-endereco');
        const telefoneElement = document.getElementById('modal-telefone');
        const estadoCivilElement = document.getElementById('modal-estadoCivil');        

        nomeElement.textContent = data.nome;
        enderecoElement.textContent = data.endereco;
        telefoneElement.textContent = data.fone;
        estadoCivilElement.textContent = data.estadoCivil;        
      })
      .catch(error => {
        console.error(error);
      });
  }
  

function openModal(clienteId) {
    fetchClienteById(clienteId);

    $('#myModal').modal('show');
  }
  
  