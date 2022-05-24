package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.*;
import com.svalero.gestitaller2.domain.dto.FacturaDTO;
import com.svalero.gestitaller2.exception.*;
import com.svalero.gestitaller2.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private RecambioRepository recambioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MotoRepository motoRepository;
    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(long id) throws FacturaNotFoundException {
        return facturaRepository.findById(id).orElseThrow(FacturaNotFoundException::new);
    }

    @Override
    public List<Factura> findByPagada(boolean pagada) {
        return facturaRepository.findByPagada(pagada);
    }

    @Override
    public Factura deleteFactura(long id) throws FacturaNotFoundException {
        Factura factura = facturaRepository.findById(id).orElseThrow(FacturaNotFoundException::new);
        facturaRepository.delete(factura);
        return factura;
    }

    @Override
    public Factura addFactura(FacturaDTO newFacturaDTO) throws
            RecambioNotFoundException, ClienteNotFoundException, MotoNotFoundException, OrdenNotFoundException {

        Recambio recambio = recambioRepository.findById(newFacturaDTO.getRecambio())
                .orElseThrow(RecambioNotFoundException::new);

        Cliente cliente = clienteRepository.findById(newFacturaDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);

        Moto moto = motoRepository.findById(newFacturaDTO.getMoto())
                .orElseThrow(MotoNotFoundException::new);

        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(newFacturaDTO.getOrdenTrabajo())
                .orElseThrow(OrdenNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Factura factura = mapper.map(newFacturaDTO, Factura.class);
        factura.setRecambio(recambio);
        factura.setCliente(cliente);
        factura.setMoto(moto);
        factura.setOrdenTrabajo(ordenTrabajo);

        return facturaRepository.save(factura);
    }

    @Override
    public Factura modifyFactura(long id, FacturaDTO newFacturaDTO) throws
            RecambioNotFoundException, ClienteNotFoundException, MotoNotFoundException, OrdenNotFoundException {

        Recambio recambio = recambioRepository.findById(newFacturaDTO.getRecambio())
                .orElseThrow(RecambioNotFoundException::new);

        Cliente cliente = clienteRepository.findById(newFacturaDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);

        Moto moto = motoRepository.findById(newFacturaDTO.getMoto())
                .orElseThrow(MotoNotFoundException::new);

        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(newFacturaDTO.getOrdenTrabajo())
                .orElseThrow(OrdenNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Factura factura = mapper.map(newFacturaDTO, Factura.class);
        factura.setId(id);
        factura.setRecambio(recambio);
        factura.setCliente(cliente);
        factura.setMoto(moto);
        factura.setOrdenTrabajo(ordenTrabajo);

        return facturaRepository.save(factura);
    }

    @Override
    public List<Factura> findByCliente(Cliente cliente) throws FacturaNotFoundException {
        List<Factura> facturas = facturaRepository.findByCliente(cliente);
        return facturas;
    }

    @Override
    public List<Factura> findByClienteAndMoto(FacturaDTO facturaDTO) throws ClienteNotFoundException, MotoNotFoundException {

        Cliente cliente = clienteRepository.findById(facturaDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);

        Moto moto = motoRepository.findById(facturaDTO.getMoto())
                .orElseThrow(MotoNotFoundException::new);

        return facturaRepository.findByClienteAndMoto(cliente, moto);
    }

    @Override
    public void deleteByMoto(FacturaDTO facturaDTO) throws MotoNotFoundException {

        Moto moto = motoRepository.findById(facturaDTO.getMoto())
                .orElseThrow(MotoNotFoundException::new);

        facturaRepository.deleteByMoto(moto);
    }

    @Override
    public Factura modifyFacturaPagada(long id, boolean pagada) throws FacturaNotFoundException {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(FacturaNotFoundException::new);
        factura.setPagada(pagada);
        return facturaRepository.save(factura);
    }
}
